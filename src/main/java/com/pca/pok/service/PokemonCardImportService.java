package com.pca.pok.service;

import com.pca.pok.dto.PokemonTcgCardDto;
import com.pca.pok.entity.PokemonCard;
import com.pca.pok.entity.PokemonSerie;
import com.pca.pok.entity.PokemonSet;
import com.pca.pok.repository.PokemonCardRepository;
import com.pca.pok.repository.PokemonSerieRepository;
import com.pca.pok.repository.PokemonSetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PokemonCardImportService {

    private final PokemonTcgApiService apiService;
    private final PokemonCardRepository cardRepo;
    private final PokemonSetRepository setRepo;
    private final PokemonSerieRepository serieRepo;

    public PokemonCardImportService(
            PokemonTcgApiService apiService,
            PokemonCardRepository cardRepo,
            PokemonSetRepository setRepo,
            PokemonSerieRepository serieRepo
    ) {
        this.apiService = apiService;
        this.cardRepo = cardRepo;
        this.setRepo = setRepo;
        this.serieRepo = serieRepo;
    }

    @Transactional
    public void importCards(int page, int pageSize) {
        var response = apiService.fetchCards(page, pageSize);
        if (response == null || response.getData() == null) {
            return;
        }

        for (PokemonTcgCardDto dto : response.getData()) {
            // 1) Vérifier si la carte existe déjà via son tcgId
            if (cardRepo.findByTcgId(dto.getId()) != null) {
                continue; // déjà en base
            }
            // 3) Créer la carte
            PokemonCard card = new PokemonCard();
            card.setTcgId(dto.getId());
            card.setName(dto.getName());
            card.setRarity(dto.getRarity());
            card.setTypes(dto.getTypes()[0]);
            card.setNumber(dto.getNumber());
            card.setImageSmall(dto.getImages().getSmall());
            card.setImageLarge(dto.getImages().getLarge());
            card.setArtiste(dto.getArtiste());
            int hp=0;
            try {
                hp=Integer.parseInt(dto.getHp());
            }catch (NumberFormatException e){
                System.out.println("hp nest pas numérique");
            }
            card.setHp(hp);
            card.setFlavorText(dto.getFlavorText());
            card.setLegalities(dto.getLegalities().toString());
            card.setSupertype(dto.getSupertype());



            cardRepo.save(card);
            // 2) Gérer le set
            PokemonSet setEntity = null;
            if (dto.getSet() != null) {
                String setId = dto.getSet().getId(); // ex: "xy1"
                // Chercher le set en base
                setEntity = setRepo.findByTcgSetId(setId).orElse(null);
                if (setEntity == null) {
                    // Créer un nouveau set
                    setEntity = new PokemonSet();

                    setEntity.setTcgSetId(setId);
                    setEntity.setName(dto.getSet().getName());
                    //setEntity.setSerie(dto.getSet().getSeries());
                    setEntity.setReleaseDate(dto.getSet().getReleaseDate());
                    setEntity.setUpdatedAt(dto.getSet().getUpdatedAt());
                    setEntity.setTotal(dto.getSet().getTotal());
                    setEntity.setLegalities(dto.getSet().getLegalities().toString());
                    setEntity.setPtcgoCode(dto.getSet().getPtcgoCode());
                    setEntity.setLogo(dto.getSet().getImages().getLogo());
                    setEntity.setSymbol(dto.getSet().getImages().getSymbol());
                    setEntity.getCards().add(card);
                }

                // Lier la carte à son set
                card.getCardSets().add(setEntity);
                setRepo.save(setEntity);

            }
            // 2) Gérer le set
            PokemonSerie serieEntity = null;
            if (dto.getSet().getSeries()!= null) {
                String serieName = dto.getSet().getSeries();
                // Chercher le set en base
                serieEntity = serieRepo.findByName(serieName).orElse(null);
                if (serieEntity == null) {
                    // Créer un nouveau set
                    serieEntity = new PokemonSerie();
                }
                serieEntity.setName(serieName);
                serieEntity.setGameType("Pokemon");
                serieEntity.getCardSets().add(setEntity);
                // Lier la carte à son set
                setEntity.setSerie(serieEntity);
                serieRepo.save(serieEntity);

            }





            //setRepo.save(setEntity); // on re-save le set
        }
    }
}
