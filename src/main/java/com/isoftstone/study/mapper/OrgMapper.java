package com.isoftstone.study.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.isoftstone.study.domain.Organization;

@Repository
public interface OrgMapper {
	Organization getOrgById(String id);

	List<Organization> queryOrgsByStatus(Boolean actived);

	List<Organization> queryOrgsByStatus_quick(Boolean actived);

}
