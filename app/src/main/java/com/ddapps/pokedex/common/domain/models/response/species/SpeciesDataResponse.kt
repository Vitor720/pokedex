package com.ddapps.pokedex.common.domain.models.response.species

class SpeciesDataResponse(val evolution_chain: PokemonEvolutionDataResponse, val varieties: List<VarietiesListDataResponse>) {
}