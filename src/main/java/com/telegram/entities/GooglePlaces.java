package com.telegram.entities;

import java.util.ArrayList;

public class GooglePlaces {
	
	private ArrayList<String> html_attributions;
	private ArrayList<Result> results;
	private String status;
	
	public class Result{
		
		private Geometry geometry;
		private String icon;
		private String id;
		private String name;
		private OpeningHours opening_hours;
		private ArrayList<Photo> photos;
		private String place_id;
		private String scope;
		private ArrayList<AltIds> alt_ids;
		private String reference;
		private ArrayList<String> types;
		private String vicinity;
		
		public String toString(){
			String result = "Name: " + name + "\n";
			result += "Place ID: " + place_id + "\n";
			result += "Types: [";
			for(String type : types){
				result += "'" + type + "',";
			}
			result += "]";
			
			return result;
		}
		public Geometry getGeometry() {
			return geometry;
		}
		public void setGeometry(Geometry geometry) {
			this.geometry = geometry;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public OpeningHours getOpening_hours() {
			return opening_hours;
		}
		public void setOpening_hours(OpeningHours opening_hours) {
			this.opening_hours = opening_hours;
		}
		public ArrayList<Photo> getPhotos() {
			return photos;
		}
		public void setPhotos(ArrayList<Photo> photos) {
			this.photos = photos;
		}
		public String getPlace_id() {
			return place_id;
		}
		public void setPlace_id(String place_id) {
			this.place_id = place_id;
		}
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		public ArrayList<AltIds> getAlt_ids() {
			return alt_ids;
		}
		public void setAlt_ids(ArrayList<AltIds> alt_ids) {
			this.alt_ids = alt_ids;
		}
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}
		public ArrayList<String> getTypes() {
			return types;
		}
		public void setTypes(ArrayList<String> types) {
			this.types = types;
		}
		public String getVicinity() {
			return vicinity;
		}
		public void setVicinity(String vicinity) {
			this.vicinity = vicinity;
		}
	}
	
	class Geometry{
		private Location location;

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}
	}
	
	class Location{
		private Double lat;
		private Double lng;
		
		public Double getLat() {
			return lat;
		}
		public void setLat(Double lat) {
			this.lat = lat;
		}
		public Double getLng() {
			return lng;
		}
		public void setLng(Double lng) {
			this.lng = lng;
		}
		
	}
	
	class OpeningHours{
		public String getOpen_now() {
			return open_now;
		}

		public void setOpen_now(String open_now) {
			this.open_now = open_now;
		}

		private String open_now;
	}
	
	class Photo{
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public ArrayList<String> getHtml_attributions() {
			return html_attributions;
		}
		public void setHtml_attributions(ArrayList<String> html_attributions) {
			this.html_attributions = html_attributions;
		}
		public String getPhoto_reference() {
			return photo_reference;
		}
		public void setPhoto_reference(String photo_reference) {
			this.photo_reference = photo_reference;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		private int height;
		private ArrayList<String> html_attributions;
		private String photo_reference;
		private int width;
	}
	
	class AltIds{
		public String getPlace_id() {
			return place_id;
		}
		public void setPlace_id(String place_id) {
			this.place_id = place_id;
		}
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		private String place_id;
		private String scope;
	}

	public ArrayList<String> getHtml_attributions() {
		return html_attributions;
	}

	public void setHtml_attributions(ArrayList<String> html_attributions) {
		this.html_attributions = html_attributions;
	}

	public ArrayList<Result> getResults() {
		return results;
	}

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}



