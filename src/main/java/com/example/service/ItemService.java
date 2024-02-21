package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	
	public Item findById(Integer id) {
		Optional<Item> optionalItem=this.itemRepository.findById(id);
		Item item=optionalItem.get();
		return item;
	}
	
	public Item update(Integer id, ItemForm itemForm) {
		//データ1件分のEntityクラスを取得
		Item item=this.findById(id);
		//Formクラスのフィールドをセット
		item.setName(itemForm.getName());
		item.setPrice(itemForm.getPrice());
		return this.itemRepository.save(item);
	}
	
	//データ削除用メソッド
	//public void delete(Integer id) {
		//this.itemRepository.deleteById(id);
	//}
	
	public Item delete(Integer id) {
		//idから該当のEntityクラスを取得
		Item item=this.findById(id);
		//EntityクラスのdeletedAtフィールドを現在日時で上書き
		item.setDeletedAt(LocalDateTime.now());
		return this.itemRepository.save(item);
	}
	
	public List<Item> findByDeletedAtIsNull() {
		return this.itemRepository.findByDeletedAtIsNull();
	}
}

