<!-- src/App.vue -->
<template>
  <div class="app">
    <h1>Collection de cartes Pokémon</h1>
    <Filters
      :series="series"
      :sets="sets"
      :type-options="typeOptions"
      :rarity-options="rarityOptions"
      :year-options="yearOptions"
      :hp-options="hpOptions"
      :selected-series="selectedSeries"
      :selected-set="selectedSet"
      @update:selected-series="selectedSeries = $event"
      @update:selected-set="selectedSet = $event"
      :filters="filters"
      @update:filters="filters = $event"
      @reset="resetFilters"
    />
    <CardGrid :cards="filteredCards" />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch } from 'vue';
import axios from 'axios';
import Filters from './components/Filters.vue';
import CardGrid from './components/CardGrid.vue';
import type { PokemonSet, PokemonCard, SelectOption } from './types';

// État pour les séries, sets et cartes
const series = ref<SelectOption[]>([]);
const sets = ref<SelectOption[]>([]);
const cards = ref<PokemonCard[]>([]);
const filteredCards = ref<PokemonCard[]>([]);

// État pour les filtres
const selectedSeries = ref<SelectOption | null>(null);
const selectedSet = ref<SelectOption | null>(null);
const filters = ref({
  type: null as SelectOption | null,
  rarity: null as SelectOption | null,
  year: null as SelectOption | null,
  hpRange: null as SelectOption | null,
  number: '',
  searchName: '',
});

// Charger les séries au démarrage
const loadSeries = async () => {
  try {
    const response = await axios.get<string[]>('http://localhost:8081/api/pokemon/series');
    series.value = response.data.map(s => ({ value: s, label: s }));
  } catch (error) {
    console.error('Erreur lors du chargement des séries:', error);
  }
};
loadSeries();

// Charger les sets quand une série est sélectionnée
watch(selectedSeries, async (newSeries) => {
  if (newSeries) {
    try {
      const response = await axios.get<PokemonSet[]>(
        `http://localhost:8081/api/pokemon/series/${newSeries.value}/sets`
      );
      sets.value = response.data.map(set => ({ value: set.id, label: set.name }));
      selectedSet.value = null;
      cards.value = [];
      filteredCards.value = [];
    } catch (error) {
      console.error('Erreur lors du chargement des sets:', error);
    }
  }
});

// Charger les cartes quand un set est sélectionné
watch(selectedSet, async (newSet) => {
  if (newSet) {
    try {
      const response = await axios.get<PokemonCard[]>(
        `http://localhost:8081/api/pokemon/sets/${newSet.value}/cards`
      );
      cards.value = response.data;
      filteredCards.value = response.data;
    } catch (error) {
      console.error('Erreur lors du chargement des cartes:', error);
    }
  }
});

// Options dynamiques pour les filtres
const typeOptions = computed(() =>
  [...new Set(cards.value.map(card => card.type))].map(t => ({ value: t, label: t }))
);
const rarityOptions = computed(() =>
  [...new Set(cards.value.map(card => card.rarity))].map(r => ({ value: r, label: r }))
);
const yearOptions = computed(() =>
  [...new Set(cards.value.map(card => card.year))].map(y => ({ value: y, label: y }))
);
const hpOptions = ref([
  { value: '0-50', label: '0-50' },
  { value: '51-100', label: '51-100' },
  { value: '101-150', label: '101-150' },
  { value: '151-', label: '151+' },
]);

// Appliquer les filtres
watch([filters, cards], () => {
  let filtered = [...cards.value];

  if (filters.value.type) {
    filtered = filtered.filter(card => card.type === filters.value.type?.value);
  }
  if (filters.value.rarity) {
    filtered = filtered.filter(card => card.rarity === filters.value.rarity?.value);
  }
  if (filters.value.year) {
    filtered = filtered.filter(card => card.year === filters.value.year?.value);
  }
  if (filters.value.hpRange) {
    const [min, max] = filters.value.hpRange.value.split('-').map(Number);
    filtered = filtered.filter(card => card.hp >= min && (max ? card.hp <= max : true));
  }
  if (filters.value.number) {
    filtered = filtered.filter(card => card.number === filters.value.number);
  }
  if (filters.value.searchName) {
    filtered = filtered.filter(card =>
      card.name.toLowerCase().includes(filters.value.searchName.toLowerCase())
    );
  }

  filteredCards.value = filtered;
});

// Réinitialiser les filtres
const resetFilters = () => {
  selectedSeries.value = null;
  selectedSet.value = null;
  filters.value = {
    type: null,
    rarity: null,
    year: null,
    hpRange: null,
    number: '',
    searchName: '',
  };
};
</script>

<style lang="scss" scoped>
@import '@/assets/main.scss';

.app {
  font-family: Arial, sans-serif;
  padding: 20px;
  background-color: #fff;

  h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
  }
}
</style>
