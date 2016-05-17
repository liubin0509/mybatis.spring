package com.isoftstone.study.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.isoftstone.study.domain.Organization;

public class OrgServiceTest extends BaseTest {

	@Autowired
	private static OrgService orgService;

	@Before
	public void init() {
		orgService = this.getService(OrgService.class);
	}

	@Test
	public void testGetOrgById() {
		String id = "58b9e1c2-4800-4f87-acfb-3964c3911627";
		Organization org = orgService.getOrgById(id);
		Assert.assertNotNull(org);
		Assert.assertEquals(id, org.getId());
	}

	@Test
	public void testQueryOrgsByStatus() {
		Boolean actived = Boolean.TRUE;
		List<Organization> orgs = orgService.queryOrgsByStatus(actived);
		Assert.assertFalse(CollectionUtils.isEmpty(orgs));
		for (Organization org : orgs) {
			Assert.assertEquals(actived, org.getActived());
//			Assert.assertNotNull(org.getOwner());
		}
	}
	@Test
	public void testQueryOrgsByStatus_quick() {
		Boolean actived = Boolean.TRUE;
		List<Organization> orgs = orgService.queryOrgsByStatus_quick(actived);
		Assert.assertFalse(CollectionUtils.isEmpty(orgs));
		for (Organization org : orgs) {
			Assert.assertEquals(actived, org.getActived());
//			Assert.assertNotNull(org.getOwner());
		}
	}
}
