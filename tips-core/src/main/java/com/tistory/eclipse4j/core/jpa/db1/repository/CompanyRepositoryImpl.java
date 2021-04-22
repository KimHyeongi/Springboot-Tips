package com.tistory.eclipse4j.core.jpa.db1.repository;

import com.tistory.eclipse4j.core.jpa.db1.JPATipDb1QuerydslRepositorySupport;
import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import com.tistory.eclipse4j.core.jpa.db1.entity.QCompany;
import com.tistory.eclipse4j.core.jpa.db1.entity.SimpleCompany;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Query;
import java.util.List;

@Slf4j
public class CompanyRepositoryImpl extends JPATipDb1QuerydslRepositorySupport implements CompanyRepositoryCustom {

	public CompanyRepositoryImpl() {
		super(Company.class);
	}

	@Override
	public Long findMaxId() {
		return from(QCompany.company).select(QCompany.company.id.max()).fetchOne();
	}

	@Override
	public SimpleCompany findSimpleCompanyById(Long id) {
		StringBuilder queryBuilder = new StringBuilder("SELECT");
		queryBuilder.append(" code,");
		queryBuilder.append(" name");
		queryBuilder.append(" FROM company ");
		queryBuilder.append(" WHERE id = :id ");
		try {
			Query query = getEntityManager().createNativeQuery(queryBuilder.toString(), "SimpleCompany");
			query.setParameter("id", id);
			return (SimpleCompany) query.getSingleResult();
		} catch (Exception e) {
			log.warn("ID : {} 에 해당하는 Company 정보가 없습니다.", id);
		}
		return new SimpleCompany();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SimpleCompany> findSimpleCompanies() {
		StringBuilder queryBuilder = new StringBuilder("SELECT");
		queryBuilder.append(" code,");
		queryBuilder.append(" name");
		queryBuilder.append(" FROM company ");
		queryBuilder.append(" LIMIT 10 ");
		Query query = getEntityManager().createNativeQuery(queryBuilder.toString(), "SimpleCompany");
		List<SimpleCompany> results = query.getResultList();
		return results;
	}

}
