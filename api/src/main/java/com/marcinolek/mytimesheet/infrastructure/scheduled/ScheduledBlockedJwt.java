package com.marcinolek.mytimesheet.infrastructure.scheduled;

import com.marcinolek.mytimesheet.dto.jwt.BlockedJwtDTO;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.jwt.BlockedJwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduledBlockedJwt {

    private final Logger logger = LoggerFactory.getLogger(ScheduledBlockedJwt.class);

    @Autowired
    private BlockedJwtService blockedJwtService;

    @Scheduled(cron = "0 0 1 * * *")
//    @Scheduled(cron = "1 * * * * *")
    public void removeOverdueBlockedTokens() throws WebApiException {
        List<BlockedJwtDTO> removedTokens = Optional.ofNullable(this.blockedJwtService.removeOverdueTokens())
                .orElse(Collections.emptyList());
        logger.info("Removed overdue JWT: " + removedTokens.size());
    }
}
