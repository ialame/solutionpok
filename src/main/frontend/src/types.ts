// src/types.ts

export interface PokemonSet {
  id: number;
  name: string;
  series: string;
  cards?: PokemonCard[];
}

export interface PokemonCard {
  id: number;
  name: string;
  type: string;
  rarity: string;
  year: number;
  hp: number;
  number: string;
  imageUrl: string;
  setName: string;
}

export interface SelectOption {
  value: string | number;
  label: string;
}
