package com.pca.pok.service;

import com.pca.pok.dto.PokemonTcgCardDto;
import com.pca.pok.dto.PokemonTcgSetDto;
import com.pca.pok.entity.PokemonCard;
import com.pca.pok.entity.PokemonSet;
import com.pca.pok.repository.PokemonCardRepository;
import com.pca.pok.repository.PokemonSetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PokemonCardImportService {

    private final PokemonSetRepository pokemonSetRepository;
    private final PokemonCardRepository pokemonCardRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public PokemonCardImportService(
            PokemonSetRepository pokemonSetRepository,
            PokemonCardRepository pokemonCardRepository
    ) {
        this.pokemonSetRepository = pokemonSetRepository;
        this.pokemonCardRepository = pokemonCardRepository;
    }

    //@Transactional // s’assurer qu’on est en transaction
    public void importAllSetsAndCards() {
        // 1) Télécharger la liste des sets
        PokemonTcgSetApiResponse setResponse = restTemplate.getForObject(
                "https://api.pokemontcg.io/v2/sets?pageSize=1000",
                PokemonTcgSetApiResponse.class
        );

        if (setResponse != null && setResponse.getData() != null) {
            for (PokemonTcgSetDto setDto : setResponse.getData()) {
                // a) Chercher en base si on a déjà un set “setDto.getId()”
                PokemonSet setEntity = pokemonSetRepository.findByTcgSetId(setDto.getId())
                        .orElse(new PokemonSet());

                // b) Mettre à jour les champs
                setEntity.setTcgSetId(setDto.getId());
                setEntity.setName(setDto.getName());
                setEntity.setSeries(setDto.getSeries());
                setEntity.setReleaseDate(setDto.getReleaseDate());
                // setEntity.setSerie(...) si tu gères la liaison Serie ↔ Set

                setEntity.setUpdatedAt(setDto.getUpdatedAt());
                setEntity.setTotal(setDto.getTotal());
                setEntity.setLegalities(setDto.getLegalities().toString());
                setEntity.setPtcgoCode(setDto.getPtcgoCode());
                setEntity.setLogo(setDto.getImages().getLogo());
                setEntity.setSymbol(setDto.getImages().getSymbol());
                //setEntity.getCards().add(card);


                pokemonSetRepository.save(setEntity);

                // 2) Importer les cartes du set
                importCardsForSet(setEntity);
            }
        }
    }
    @Transactional
    protected void importCardsForSet(PokemonSet setEntity) {
        // a) URL : https://api.pokemontcg.io/v2/cards?q=set.id:" + setEntity.getTcgSetId()
        //String url = "https://api.pokemontcg.io/v2/cards?q=set.id:\"" + setEntity.getTcgSetId() + "\"&pageSize=1000";
        String url = UriComponentsBuilder.fromHttpUrl("https://api.pokemontcg.io/v2/cards")
                .queryParam("q", "set.id:\"" + setEntity.getTcgSetId() + "\"")
                .queryParam("pageSize", 1000)
                .encode() // Encodage automatique des caractères spéciaux
                .toUriString().replace("%22","\"").replace("%20"," ");
        PokemonTcgCardApiResponse cardResponse = restTemplate.getForObject(url, PokemonTcgCardApiResponse.class);

        if (cardResponse != null && cardResponse.getData() != null) {
            for (PokemonTcgCardDto cardDto : cardResponse.getData()) {
                // b) Chercher en base
                PokemonCard cardEntity = pokemonCardRepository.findByTcgId(cardDto.getId());
                if(cardEntity == null) {
                    cardEntity = new PokemonCard();
                }
                // c) Mettre à jour
                cardEntity.setTcgId(cardDto.getId());
                cardEntity.setName(cardDto.getName());
                cardEntity.setRarity(cardDto.getRarity());
                // ...
                //if(cardDto.getImages() != null) {
                //    cardEntity.setTypes(cardDto.getTypes()[0]);
                //}
                cardEntity.setNumber(cardDto.getNumber());
                cardEntity.setImageSmall(cardDto.getImages().getSmall());
                cardEntity.setImageLarge(cardDto.getImages().getLarge());
                cardEntity.setArtiste(cardDto.getArtiste());
                int hp=0;
                try {
                    hp=Integer.parseInt(cardDto.getHp());
                }catch (NumberFormatException e){
                    System.out.println("hp nest pas numérique");
                }
                cardEntity.setHp(hp);
                cardEntity.setFlavorText(cardDto.getFlavorText());
                cardEntity.setLegalities(cardDto.getLegalities().toString());
                cardEntity.setSupertype(cardDto.getSupertype());

                // d) Lier la carte au set (ManyToMany ou ManyToOne selon ton design)
                // Ex si ManyToMany :
                cardEntity.getSets().add(setEntity);
                setEntity.getCards().add(cardEntity);

                pokemonCardRepository.save(cardEntity);
            }
        }
    }
}

