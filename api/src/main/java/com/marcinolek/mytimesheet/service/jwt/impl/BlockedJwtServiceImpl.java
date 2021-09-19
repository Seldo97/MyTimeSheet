package com.marcinolek.mytimesheet.service.jwt.impl;

import com.marcinolek.mytimesheet.dto.jwt.BlockedJwtDTO;
import com.marcinolek.mytimesheet.entity.jwt.BlockedJwtEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.repository.jwt.BlockedJwtRepository;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudServiceImpl;
import com.marcinolek.mytimesheet.service.jwt.BlockedJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlockedJwtServiceImpl extends AbstractCrudServiceImpl<BlockedJwtEntity, BlockedJwtDTO, Long>
        implements BlockedJwtService {

    @Autowired
    private BlockedJwtRepository blockedJwtRepository;

    @Value("${jwt.blockTimeInDays}")
    private int blockTimeInDays;

    @Override
    public boolean isTokenBlocked(String token) {
        return this.blockedJwtRepository.findByToken(token).isPresent();
    }

    @Override
    public BlockedJwtDTO getByToken(String token) {
        return this.mapper.toDto(this.blockedJwtRepository.findByToken(token).orElse(null));
    }

    @Override
    public List<BlockedJwtDTO> getOverdueTokens() {
        return this.mapper.toDtoList(this.blockedJwtRepository.findAllByEndBlockDateBefore(LocalDate.now()));
    }

    @Override
    @Async
    public List<BlockedJwtDTO> removeOverdueTokens() throws WebApiException {
        List<BlockedJwtDTO> tokensToRemove = this.getOverdueTokens();
        this.deleteAll(tokensToRemove);
        return tokensToRemove;
    }

    @Override
    public BlockedJwtDTO blockToken(String token) throws WebApiException {
        return this.create(new BlockedJwtDTO(token, LocalDate.now().plusDays(blockTimeInDays)));
    }
}
