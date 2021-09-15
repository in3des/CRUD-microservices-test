package com.in3des.testCRUD.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.in3des.testCRUD.model.GameItem;
import com.in3des.testCRUD.model.enums.Status;
import com.in3des.testCRUD.services.GameItemService;
import org.springframework.stereotype.Service;

import com.in3des.testCRUD.repository.GameItemRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class GameItemServiceImpl implements GameItemService {

	private final GameItemRepository repo;

	public GameItemServiceImpl(GameItemRepository repo) {
		this.repo = repo;
	}

	@Override
//	@Transactional
	public GameItem create(GameItem item) {
		return repo.save(item);
	}

	@Override
//	@Transactional(readOnly = true)
	public List<GameItem> listAll(){
//		return repo.findAll();
		return repo.findAllByOrderByIdAsc();
	}

	@Override
//	@Transactional(readOnly = true)
	public void save(GameItem gameItem) {
		repo.save(gameItem);
	}

	@Override
//	@Transactional(readOnly = true)
	public GameItem get(Long id) {
		return repo.findById(id).get();
	}

	@Override
//	@Transactional(readOnly = true)
	public void delete(Long id) {
		repo.deleteById(id);
	}


	@PostConstruct
	private void createTestGameItem() {

		List<GameItem> list = new ArrayList<>();

		list.add(new GameItem(1L, "Item1", Status.RELEASE, "01-01-2001"));
		list.add(new GameItem(2L, "Item2", Status.RELEASE, "01-01-2002"));
		list.add(new GameItem(3L, "Item3", Status.RELEASE, "01-01-2003"));
		list.add(new GameItem(4L, "Item4", Status.RELEASE, "01-01-2004"));
		list.add(new GameItem(5L, "Item5", Status.RELEASE, "01-01-2005"));
		list.add(new GameItem(6L, "Item6", Status.PRODUCTION, "01-01-2006"));
		list.add(new GameItem(7L, "Item7", Status.PRODUCTION, "01-01-2007"));
		list.add(new GameItem(8L, "Item8", Status.PRODUCTION, "01-01-2008"));
		list.add(new GameItem(9L, "Item9", Status.PRODUCTION, "01-01-2009"));
		list.add(new GameItem(10L, "Item10", Status.PRODUCTION, "01-01-2011"));
		list.add(new GameItem(11L, "Item11", Status.PRODUCTION, "01-01-2011"));

		repo.saveAll(list);
	}


//

}
