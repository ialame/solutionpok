<!-- src/components/Filters.vue -->
<template>
  <div class="filters">
    <div class="filter-item">
      <label>Série</label>
      <select :value="selectedSeries" @change="$emit('update:selected-series', $event.target.value)">
        <option :value="null" disabled>Sélectionner une série</option>
        <option v-for="serie in series" :key="serie.value" :value="serie">
          {{ serie.label }}
        </option>
      </select>
    </div>
    <div class="filter-item">
      <label>Set</label>
      <select :value="selectedSet" @change="$emit('update:selected-set', $event.target.value)" :disabled="!selectedSeries">
        <option :value="null" disabled>Sélectionner un set</option>
        <option v-for="set in sets" :key="set.value" :value="set">
          {{ set.label }}
        </option>
      </select>
    </div>
    <div class="filter-item">
      <label>Type</label>
      <select :value="filters.type" @change="updateFilter('type', $event.target.value)" :disabled="!selectedSet">
        <option :value="null">Sélectionner un type</option>
        <option v-for="type in typeOptions" :key="type.value" :value="type">
          {{ type.label }}
        </option>
      </select>
    </div>
    <div class="filter-item">
      <label>Rareté</label>
      <select :value="filters.rarity" @change="updateFilter('rarity', $event.target.value)" :disabled="!selectedSet">
        <option :value="null">Sélectionner une rareté</option>
        <option v-for="rarity in rarityOptions" :key="rarity.value" :value="rarity">
          {{ rarity.label }}
        </option>
      </select>
    </div>
    <div class="filter-item">
      <label>Année</label>
      <select :value="filters.year" @change="updateFilter('year', $event.target.value)" :disabled="!selectedSet">
        <option :value="null">Sélectionner une année</option>
        <option v-for="year in yearOptions" :key="year.value" :value="year">
          {{ year.label }}
        </option>
      </select>
    </div>
    <div class="filter-item">
      <label>PV</label>
      <select :value="filters.hpRange" @change="updateFilter('hpRange', $event.target.value)" :disabled="!selectedSet">
        <option :value="null">Sélectionner une plage de PV</option>
        <option v-for="hp in hpOptions" :key="hp.value" :value="hp">
          {{ hp.label }}
        </option>
      </select>
    </div>
    <div class="filter-item">
      <label>Numéro</label>
      <input
        type="text"
        :value="filters.number"
        @input="updateFilter('number', $event.target.value)"
        placeholder="Ex. 1/127"
        :disabled="!selectedSet"
      />
    </div>
    <div class="filter-item">
      <label>Rechercher par nom</label>
      <input
        type="text"
        :value="filters.searchName"
        @input="updateFilter('searchName', $event.target.value)"
        placeholder="Rechercher une carte"
        :disabled="!selectedSet"
      />
    </div>
    <div class="filter-buttons">
      <button @click="$emit('update:filters', filters)" class="apply-button">
        Appliquer les filtres
      </button>
      <button @click="$emit('reset')" class="reset-button">
        Réinitialiser
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps, defineEmits } from 'vue';
import type { SelectOption } from '../types';

const props = defineProps<{
  series: SelectOption[];
  sets: SelectOption[];
  typeOptions: SelectOption[];
  rarityOptions: SelectOption[];
  yearOptions: SelectOption[];
  hpOptions: SelectOption[];
  selectedSeries: SelectOption | null;
  selectedSet: SelectOption | null;
  filters: {
    type: SelectOption | null;
    rarity: SelectOption | null;
    year: SelectOption | null;
    hpRange: SelectOption | null;
    number: string;
    searchName: string;
  };
}>();

const emit = defineEmits<{
  (e: 'update:selected-series', value: SelectOption | null): void;
  (e: 'update:selected-set', value: SelectOption | null): void;
  (e: 'update:filters', value: any): void;
  (e: 'reset'): void;
}>();

// Fonction pour mettre à jour les filtres
const updateFilter = (key: keyof typeof props.filters, value: any) => {
  const updatedFilters = { ...props.filters, [key]: value };
  emit('update:filters', updatedFilters);
};
</script>

<style lang="scss" scoped>
.filters {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
  margin-bottom: 20px;
  padding: 10px;
  background-color: #f5f5f5;
  border-radius: 5px;
}

.filter-item {
  flex: 1;
  min-width: 200px;

  label {
    display: block;
    margin-bottom: 5px;
    font-size: 14px;
  }

  select,
  input {
    width: 100%;
    padding: 5px;
    font-size: 14px;
    font-family: Arial, sans-serif;
  }
}

.filter-buttons {
  display: flex;
  gap: 10px;
  align-items: flex-end;
}

.apply-button {
  padding: 8px 15px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.reset-button {
  padding: 8px 15px;
  background-color: #dc3545;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
