package com.jeancorzo.rickandmorty.episodes.service.dto

import com.jeancorzo.rickandmorty.service.dto.InfoDto
import com.jeancorzo.rickandmorty.service.dto.ListDto

data class EpisodeListDto(
    override val info: InfoDto,
    override val data: ArrayList<EpisodeDto>
) : ListDto<EpisodeDto>(info, data)

