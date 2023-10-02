package com.jeancorzo.rickandmorty.characters.service.dto.characters

import com.jeancorzo.rickandmorty.characters.service.dto.InfoDto
import com.jeancorzo.rickandmorty.characters.service.dto.ListDto

data class CharacterListDto(
    override val info: InfoDto,
    override val data: ArrayList<CharacterDto>
) : ListDto<CharacterDto>(info, data)

