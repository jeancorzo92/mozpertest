package com.jeancorzo.rickandmorty.characters.service.dto

import com.jeancorzo.rickandmorty.service.dto.InfoDto
import com.jeancorzo.rickandmorty.service.dto.ListDto

data class CharacterListDto(
    override val info: InfoDto,
    override val data: ArrayList<CharacterDto>
) : ListDto<CharacterDto>(info, data)

