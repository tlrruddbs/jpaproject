package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.entity.item.Items;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Items item) {
		itemRepository.save(item);
	}
	
	public List <Items> findItems(){
		return itemRepository.findAll();
	}
	
	public Items findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}
