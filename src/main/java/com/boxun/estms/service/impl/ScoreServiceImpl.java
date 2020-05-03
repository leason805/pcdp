package com.boxun.estms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.dao.impl.ScoreDaoImpl;
import com.boxun.estms.entity.EScore;
import com.boxun.estms.service.IScoreService;

@Service("scoreService")
public class ScoreServiceImpl implements IScoreService{

	@Autowired
	private ScoreDaoImpl scoreDao;

	@Override
	public void create(EScore score) {
		this.scoreDao.save(score);
	}

	@Override
	public void update(EScore score) {
		this.scoreDao.update(score);
	}

	@Override
	public void delete(EScore score) {
		this.scoreDao.delete(score);
	}

	@Override
	public EScore load(Long id) {
		return this.scoreDao.load(id);
	}

	@Override
	public List<EScore> list() {
		return this.scoreDao.loadAll();
	}

}
