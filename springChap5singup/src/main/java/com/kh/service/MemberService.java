package com.kh.service;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mapper.MembersMapper;
import com.kh.model.Member;

@Service
public class MemberService {

    @Autowired
    private MembersMapper membersMapper;
    
    @Transactional(rollbackFor = Exception.class)
    public void signUpMember(Member member) {
        membersMapper.insertMember(member);
    }
}
