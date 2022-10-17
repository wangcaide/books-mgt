package com.github.wangcaide.service;

import com.github.wangcaide.common.constant.SysConstant;
import com.github.wangcaide.common.enums.CommonStatus;
import com.github.wangcaide.common.enums.ContactType;
import com.github.wangcaide.common.enums.YesOrNo;
import com.github.wangcaide.entity.*;
import com.github.wangcaide.mapper.*;
import com.github.wangcaide.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * Account Service层
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 16:05:43
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;
    private final ContactMapper contactMapper;
    private final PersonMapper personMapper;
    private final PersonAccountRelationMapper personAccountRelationMapper;

    private final AccountRoleRelationMapper accountRoleRelationMapper;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AccountVo register(AccountVo vo) {

        //todo username 及 phone number 数据库判重，数据库唯一索引
        Date current = new Date();
        AccountEntity account = new AccountEntity();
        account.setUsername(StringUtils.isBlank(vo.getUsername()) ? vo.getPhoneNumber() : vo.getUsername());
        account.setPassword(passwordEncoder.encode(vo.getPassword()));
        account.setStatus(CommonStatus.VALID.getDbValue());
        account.setCreatedTime(current);
        account.setCreatedBy(SysConstant.SYSTEM_USER);
        account.setUpdatedTime(current);
        account.setUpdatedBy(SysConstant.SYSTEM_USER);
        accountMapper.insert(account);

        PersonEntity person = new PersonEntity();
        person.setFirstName(vo.getFirstName());
        person.setLastName(vo.getLastName());
        person.setIdType(vo.getIdType());
        person.setIdValue(vo.getIdValue());
        person.setStatus(CommonStatus.VALID.getDbValue());
        person.setCreatedTime(current);
        person.setCreatedBy(SysConstant.SYSTEM_USER);
        person.setUpdatedTime(current);
        person.setUpdatedBy(SysConstant.SYSTEM_USER);
        personMapper.insert(person);

        PersonAccountRelationEntity relation = new PersonAccountRelationEntity();
        relation.setAccountId(account.getAccountId());
        relation.setPersonId(person.getPersonId());
        relation.setStatus(CommonStatus.VALID.getDbValue());
        relation.setCreatedTime(current);
        relation.setCreatedBy(SysConstant.SYSTEM_USER);
        relation.setUpdatedTime(current);
        relation.setUpdatedBy(SysConstant.SYSTEM_USER);
        personAccountRelationMapper.insert(relation);

        ContactEntity contact = new ContactEntity();
        contact.setContactType(ContactType.PHONE_NUMBER.getDbCode());
        contact.setContactInfo(vo.getPhoneNumber());
        contact.setStatus(CommonStatus.VALID.getDbValue());
        contact.setPersonId(person.getPersonId());
        contact.setIsPrimary(YesOrNo.YES.getDbValue());
        contact.setCreatedTime(current);
        contact.setCreatedBy(SysConstant.SYSTEM_USER);
        contact.setUpdatedTime(current);
        contact.setUpdatedBy(SysConstant.SYSTEM_USER);
        contactMapper.insert(contact);


        int readerRoleId = roleMapper.findReaderRoleId();
        AccountRoleRelationEntity accountRoleRelation = new AccountRoleRelationEntity();
        accountRoleRelation.setRoleId(readerRoleId);
        accountRoleRelation.setAccountId(account.getAccountId());
        accountRoleRelation.setStatus(CommonStatus.VALID.getDbValue());
        accountRoleRelation.setCreatedTime(current);
        accountRoleRelation.setCreatedBy(SysConstant.SYSTEM_USER);
        accountRoleRelation.setUpdatedTime(current);
        accountRoleRelation.setUpdatedBy(SysConstant.SYSTEM_USER);
        accountRoleRelationMapper.insert(accountRoleRelation);

        vo.setAccountId(String.valueOf(account.getAccountId()));
        return vo;
    }

}
