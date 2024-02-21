package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	private final ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService=itemService;
	}
	
	//商品一覧の表示
	@GetMapping
	public String index(Model model) {
		//データの疎通確認
		List<Item> items=this.itemService.findAll();//画面で利用する変数としてitemsをセット
		//コンソールよりListの中身を確認
		//System.out.println(items.toString());
		model.addAttribute("items", items);
		return "item/index";
	}
	
	//商品登録ページ表示
	@GetMapping("toroku")
	public String torokuPage(@ModelAttribute("itemForm") ItemForm itemForm) {
		//templates/items/torokuPage.htmlを表示
		return "item/torokuPage";
	}
	
	//商品登録の実行
	@PostMapping("toroku")
	public String toroku(ItemForm itemForm) {
		this.itemService.save(itemForm);//一覧ページへリダイレクト
		return "redirect:/item";
	}
	
	//商品編集ページ
	@GetMapping("henshu/{id}")
	public String henshuPage(@PathVariable("id") Integer id, Model model, @ModelAttribute("itemForm") ItemForm itemForm) {
		//Entityクラスのインスタンスをidより検索し、取得
		Item item=this.itemService.findById(id);
		//フィールドのセット
		itemForm.setName(item.getName());
		itemForm.setPrice(item.getPrice());
		model.addAttribute("id", id);
		return "item/henshuPage";
	}
	
	//商品編集の実行
	@PostMapping("henshu/{id}")
	public String henshu(@PathVariable("id") Integer id, @ModelAttribute("itemForm") ItemForm itemForm) {
		this.itemService.update(id, itemForm);
		return "redirect:/item";
	}
	
	//商品削除
	@PostMapping("sakujo/{id}")
	public String sakujo(@PathVariable("id") Integer id) {
		this.itemService.delete(id);
		return "redirect:/item";
	}
	
}
