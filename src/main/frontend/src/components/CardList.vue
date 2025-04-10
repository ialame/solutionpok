<template>
  <div>
    <h2>Liste des cartes</h2>
    <ul>
      <li v-for="card in cards" :key="card.id">
        {{ card.name }} (Rareté: {{ card.rarity }})
        <img :src="card.imageSmall" />
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'

// Définis l'interface TypeScript pour tes cartes
interface Card {
  id: number
  name: string
  rarity: string
  imageSmall: string
  // ajoute d'autres champs si besoin : hp, tcgId, etc.
}

// On déclare la liste de cartes en "ref", pour réactivité
const cards = ref<Card[]>([])

// Une fonction async pour charger les cartes depuis le backend
async function loadCards() {
  // Met l'URL de ton API Spring Boot (ex: "http://localhost:8080/api/pokemon-cards")
  const response = await axios.get<Card[]>('http://localhost:8081/api/cards')
  cards.value = response.data
}
const xyz =5;
console.log(xyz)
// Au montage du composant, on charge les cartes
onMounted(() => {
  loadCards()
  const xyz =5;
  console.log(xyz)
})
</script>

<style scoped>
/* Style local, optionnel */
background-image {
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
  height: 250px;
}
</style>
