package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.repository.ItemRepository;

@Service
public class ItemService {
	private final ItemRepository itemRepository;
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository=itemRepository;
	}
	
	//データ保存用のメソッド
	public Item save(ItemForm itemForm) {
		Item item=new Item();
		//フィールドのセット
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		//repository.saveを利用してデータの保存を行う
		return this.itemRepository.save(item);
	}
	
	public List<Item> findAll() {
		return this.itemRepository.findAll();
	}
	
}

