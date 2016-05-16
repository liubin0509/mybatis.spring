package com.isoftstone.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isoftstone.study.domain.Organization;
import com.isoftstone.study.mapper.OrgMapper;

@Service
public class OrgService {
	@Autowired
	private OrgMapper orgMapper;

	public Organization getOrgById(String id) {
		return orgMapper.getOrgById(id);
	}

	public List<Organization> queryOrgsByStatus(Boolean actived) {
		return orgMapper.queryOrgsByStatus(actived);
	}

	public List<Organization> queryOrgsByStatus_quick(Boolean actived) {
		return orgMapper.queryOrgsByStatus_quick(actived);
	}


}
