package com.telegram.entities;

import java.util.ArrayList;

public class WikiMedia {

	public Query query;
	
	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public class Query{
		
		private ArrayList<Page> pages;
		
		public ArrayList<Page> getPages() {
			return pages;
		}

		public void setPages(ArrayList<Page> pages) {
			this.pages = pages;
		}

	}
	
	public class Page{
		private int pageid;
		private int ns;
		private String title;
		private int index;
		private ArrayList<Category> categories;
		
		public String toString(){
			String result = "Page ID: " + pageid + "\n";
			result += "Title: " + title + "\n[";
			for(Category cat : categories){
				result += "'" + cat + "',";
			}
			return result;
		}
		
		public int getPageid() {
			return pageid;
		}
		public void setPageid(int pageid) {
			this.pageid = pageid;
		}
		public int getNs() {
			return ns;
		}
		public void setNs(int ns) {
			this.ns = ns;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public ArrayList<Category> getCategories() {
			return categories;
		}
		public void setCategories(ArrayList<Category> categories) {
			this.categories = categories;
		}
		
	}
	
	public class Category{
		private int ns;
		private String title;
		
		public int getNs() {
			return ns;
		}
		public void setNs(int ns) {
			this.ns = ns;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
	}
}
