package com.bridgelabz.qa.api.a;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Rest_Api_Automation_Testing_Test {

	String token;
	String userId;
	String playlistID;
	
	@BeforeTest
	
	public void getToken() {
		token ="Bearer BQB40HRz22ww_QYQh1K1SUgCz6r6iLbKSUrjNrLAhwJ_8SxCWAJHEFV7_nfMrFOkYIdH2vugvLjKRLpkV0EYd2wzN8vZO3D9UC4yY_VW_EXS5J2zHjLMSLo31_7SIwfNshBTwVJeRKc6YkIwEs-i2ttqVwQRXBXJYWJzMtYdkEa0d8W1tVOafeZviWSIt02KZv4Oo1wKBPDRTl4C1wv6FCxMkgbgw6iGnKuGiJMAF-Y5IFhHm-nLz9PahE-qFmyAd96x3pi46g9R5TRPmNzC5QGnjz4ULesNaUZq4w";
	}
	
	//Users Profile
	
	
	@Test (priority=1)
	
	public void get_Current_User_Profile() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		userId = response.path("id");
		System.out.println("Current user id is:" + userId);
		Assert.assertEquals("312co3qtjo7besp2ym2d6mnbgyfe", userId);
		
	}
	
	@Test (priority=2)
	
	public void get_user_profile() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userId+"");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		userId = response.path("id");
		System.out.println("Current user id is:" + userId);
		Assert.assertEquals("312co3qtjo7besp2ym2d6mnbgyfe", userId);
	}
	
	
	//Playlist
	
	@Test (priority=3)
	
	public void create_playlist() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"CQA-113-Automation playlist\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/"+userId+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
		playlistID = response.path("id");
		System.out.println("CQA-113-Atomation playlist  is" + playlistID);
		
		
	}
	
	
	@Test (priority=4)
	
	public void Add_Items_to_Playlist() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("uris","spotify:track:5cHZeQnlnEpPCdroiewbdx")
				.when()
				.post("https://api.spotify.com/v1/playlists/7MNDR77qQhwD3KtxzlAyVl/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	@Test (priority=5)
	
	public void Get_User_Playlists() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+userId+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		userId = response.path("id");
		System.out.println("Current user id is:" + userId);
		Assert.assertEquals("312co3qtjo7besp2ym2d6mnbgyfe", userId);
		
	}
	
	
	@Test (priority=6)
	
	public void get_Playlist() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/7MNDR77qQhwD3KtxzlAyVl");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	@Test (priority=7)
	
	public void get_Playlist_Items() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/playlists/7MNDR77qQhwD3KtxzlAyVl/tracks");
		response.prettyPrint();
		
	}
	
	
	@Test (priority=8)
	
	public void get_Playlist_Cover_Image() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/7MNDR77qQhwD3KtxzlAyVl/images\r\n");
		response.prettyPrint();
		
	}
	
	
	@Test (priority=9)
	
	public void get_Current_User_Playlists() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists/");
		response.prettyPrint();
		
	}
	
	
	@Test (priority=10)
	
	public void remove_Playlist_Items() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.delete("https://api.spotify.com/v1/playlists/{playlist_id}/tracks\r\n");
		response.prettyPrint();
	}
	
	
	
	//Search
	
	@Test (priority=11)
	
	public void search() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("q","shreya ghoshal")
				.queryParam("type","track")
				.when()
				.get("https://api.spotify.com/v1/search");
		response.prettyPrint();
	}
	
	
	
	//Tracks
	 
	@Test (priority=12)
	
	public void get_Track_Audio_Analysis() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-analysis/5cHZeQnlnEpPCdroiewbdx");
		response.prettyPrint();
		
		
	}
	
	
	@Test (priority=13)
	
	public void get_Track_Audio() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids", "7MNDR77qQhwD3KtxzlAyVl")
				.when()
				.get("	https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		
	}
	
	
	@Test (priority=14)
	
	public void get_Track_Audio_Features() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids","5cHZeQnlnEpPCdroiewbdx")
				.when()
				.get("https://api.spotify.com/v1/audio-features/5cHZeQnlnEpPCdroiewbdx");
		response.prettyPrint();
		
	}
	
	
	@Test (priority=15)
	
	public void get_Several_Tracks() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids","5cHZeQnlnEpPCdroiewbdx")
				.when()
				.get("https://api.spotify.com/v1/tracks");
		response.prettyPrint();
		
	}
	
	@Test (priority=16)
	
	public void get_Track() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids","5cHZeQnlnEpPCdroiewbdx")
				.when()
				.get("https://api.spotify.com/v1/tracks/5cHZeQnlnEpPCdroiewbdx");
		response.prettyPrint();
		
	}
	
	
	
	//Shows
	
	@Test (priority=17)
	
	public void get_Several_Shows() {
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", token)
				.queryParam("ids","7MNDR77qQhwD3KtxzlAyVl")
				.when()
				.get("https://api.spotify.com/v1/shows");
		response.prettyPrint();
		
	}
	
	

}

