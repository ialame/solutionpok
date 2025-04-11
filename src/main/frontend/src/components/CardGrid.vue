<!-- src/components/CardGrid.vue -->
<template>
  <div class="card-grid">
    <div v-if="cards.length > 0">
      <div v-for="card in cards" :key="card.id" class="card-item">
        <img :src="card.imageUrl" :alt="card.name" class="card-image" />
        <div class="card-info">
          Nom : {{ card.name }} | Extension : {{ card.setName }} | Année : {{ card.year }} | Type : {{ card.type }}
        </div>
      </div>
    </div>
    <p v-else>Aucune carte à afficher. Sélectionnez une série et un set pour commencer.</p>
  </div>
</template>

<script setup lang="ts">
import type { PokemonCard } from '../types';

defineProps<{
  cards: PokemonCard[];
}>();
</script>

<style lang="scss" scoped>
.card-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 10px;
  max-height: 70vh;
  overflow-y: auto;
}

.card-item {
  text-align: center;
}

.card-image {
  width: 100%;
  max-width: 200px;
  height: auto;
}

.card-info {
  font-size: 12px;
  margin-top: 5px;
}
</style>
