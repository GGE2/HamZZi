package com.ssafy.api.service;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.repository.GuildRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class GuildServiceImpl implements GuildService {

    private final GuildRepository guildRepo;
    private final UserRepository userRepo;

    @Override
    public Guild foundGuild(String guildName) {
        Guild guild = new Guild();

        //길드명 중복체크 해 말아?
        guild.setGuild_name(guildName);
        guildRepo.saveGuild(guild);

        return guild;
    }

    @Override
    public void grantAdmin(String founderName) {
        //길드 소속여부와 어드민여부 체크
        //소속 안되어있거나 이미 어드민이면 오류발생
        //소속OK -> 어드민부여 후 저장
    }
}
