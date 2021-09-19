package com.marcinolek.mytimesheet.service.jwt;

import com.marcinolek.mytimesheet.dto.jwt.BlockedJwtDTO;
import com.marcinolek.mytimesheet.entity.jwt.BlockedJwtEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;

import java.util.List;

public interface BlockedJwtService extends AbstractCrudService<BlockedJwtEntity, BlockedJwtDTO, Long> {

    boolean isTokenBlocked(String token);

    BlockedJwtDTO getByToken(String token);

    List<BlockedJwtDTO> getOverdueTokens();

    List<BlockedJwtDTO> removeOverdueTokens() throws WebApiException;

    BlockedJwtDTO blockToken(String token) throws WebApiException;

}
