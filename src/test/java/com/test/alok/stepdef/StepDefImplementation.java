package com.test.alok.stepdef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.cucumber.java.Before;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers.*;
import org.junit.Assert;

import com.test.alok.TestData;

public class StepDefImplementation {

	private String url;
	private Response response;
	private PostsRequestData obj;
	private CommentsRequestData objComments;

	@Before
	public void setup() {
		// TODO Auto-generated method stub
		// inflating request parameters for PostRequest for posts
		System.out.println("Inside Setup call::");
		obj = new PostsRequestData();
		obj.setUserId(TestData.UserId);
		obj.setId(TestData.Id);
		obj.setTitle(TestData.title);
		obj.setBody(TestData.body);

		// Inflating request payload to be used for POST request for updating comments
		objComments = new CommentsRequestData();
		objComments.setPostId(TestData.postId);
		objComments.setId(TestData.commentsId);
		objComments.setName(TestData.name);
		objComments.setEmail(TestData.email);
		objComments.setBody(TestData.commentsBody);

	}

	/*
	 * This will check if the end point is up and running with status code match and
	 * assertions for same
	 */
	@Given("^The endpoint is up and running for \"([^\"]*)\"$")
	public void the_endpoint_is_up_and_running_for_something(String url) {
		try {
			this.url = url;
			given().contentType(ContentType.JSON);
			response = given().when().get(url);
			Assert.assertEquals(200, response.getStatusCode());
			// Below sys out is is just for debug

			System.out.println("Status code in get operation::" + response.getStatusCode());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * The below two methods are to validate POST call to add a new post using /post
	 * end point and then verify the newly added post using a get call to /post end
	 * points
	 */

	@When("User performs a post request for adding a new post")
	public void user_performs_a_post_request_for_adding_a_new_post() {

		System.out.println("Performed a post request:::");
		try {
			RestAssured.baseURI = url;
			RequestSpecification request = RestAssured.given().when();
			request.header("Content-Type", "application/json");

			response = request.body("{\"userId\":2,\"id\":1,\"title\":\"foo\",\"body\":\"bar\"}")
					.post("https://jsonplaceholder.typicode.com/posts");
			System.out.println("Status code in post operation ::" + response.getStatusCode());
			//We could also use soft Assert , but I am using Assert to ensure the failures
			Assert.assertEquals(201, response.getStatusCode());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Code to compare to check if a newly added post is present in the posts list
	@Then("User performs a get post to validate the new post")
	public void user_performs_a_get_post_to_validate_the_new_post() {
		try {

			response = when().get((url + "posts"));
			ArrayList<String> titles = response.then().extract().path("title");
			ArrayList<Integer> id = response.then().extract().path("id");
			ArrayList<Integer> userId = response.then().extract().path("userId");
			ArrayList<String> body = response.then().extract().path("body");
			boolean isPostUpdated = false;

			for (String a : titles) {
				// System.out.println("The amount value fetched is " + a);
				// System.out.println("The value obj.getTitle() " + obj.getTitle());
				if (a.contentEquals(obj.getTitle())) {
					isPostUpdated = true;
					break;
				} else {
					isPostUpdated = false;
				}

			}

			for (Integer a : id) {
				System.out.println("The amount value fetched is " + a);
				if (String.valueOf(a).contentEquals(obj.getId())) {
					System.out.println("Inside ID values macteched::");
					isPostUpdated = true;
					break;
				} else {
					System.out.println("Inside ID values not macteched::");
					isPostUpdated = false;
				}

			}
			for (Integer a : userId) {
				// System.out.println("The amount value fetched is " + a);
				if (String.valueOf(a).contentEquals(obj.getUserId())) {
					isPostUpdated = true;
					break;
				} else {
					isPostUpdated = false;
				}

			}
			for (String a : body) {
				// System.out.println("The amount value fetched is " + a);
				if (a.contentEquals(obj.getBody())) {
					isPostUpdated = true;
					break;
				} else {
					isPostUpdated = false;
				}

			}

			if (isPostUpdated) {
				Assert.assertTrue(true);
				System.out.println("Assertion true::::");
			} else {
				System.out.println("Assertion false::::");
				Assert.assertFalse(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Below two implementations are to validate a POST request to /comments end
	 * point , followed by a GET call to /comments endpoint to validate if the newly
	 * added comment is present
	 */
	@When("User performs a post request for adding a new comment")
	public void user_performs_a_post_request_for_adding_a_new_comment() {
		// Write code here that turns the phrase above into concrete actions
		try {
			RestAssured.baseURI = url;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			response = request.body(
					"{\"postId\":84,\"id\":418,\"name\":\"nesciunt voluptates amet sint et delectus et dolore culpa\",\"email\":\"Tessie@emilie.co.uk\",\"body\":\"animi est eveniet officiis qui\naperiam dolore occaecati enim aut reiciendis\nanimi ad sint labore blanditiis adipisci voluptatibus eius error\nomnis rerum facere architecto occaecati rerum\"}")
					.post("https://jsonplaceholder.typicode.com/comments");
			System.out.println("Status code in post comment operation ::" + response.getStatusCode());
			Assert.assertEquals(201, response.getStatusCode());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("User performs a get comment to validate the new comment")
	public void user_performs_a_get_comment_to_validate_the_new_comment() {
		// Write code here that turns the phrase above into concrete actions
		try {

			response = when().get((url + "comments"));
			ArrayList<Integer> postId = response.then().extract().path("postId");
			ArrayList<Integer> id = response.then().extract().path("id");
			ArrayList<String> name = response.then().extract().path("name");
			ArrayList<String> body = response.then().extract().path("body");
			ArrayList<String> email = response.then().extract().path("email");
			boolean isPostUpdated = false;

			for (Integer a : postId) {
				// System.out.println("The amount value fetched is " + a);
				// System.out.println("The value obj.getPostId " + objComments.getPostId());
				if (String.valueOf(a).contentEquals(objComments.getPostId())) {
					isPostUpdated = true;
					break;
				} else {
					isPostUpdated = false;
				}

			}

			for (Integer a : id) {
				System.out.println("The amount value fetched is " + a);
				if (String.valueOf(a).contentEquals(objComments.getId())) {
					System.out.println("Inside ID values macteched::");
					isPostUpdated = true;
					break;
				} else {
					System.out.println("Inside ID values not macteched::");
					isPostUpdated = false;
				}

			}
			for (String a : name) {
				System.out.println("The amount value fetched is " + a);
				if (a.contentEquals(objComments.getName())) {
					isPostUpdated = true;
					break;
				} else {
					isPostUpdated = false;
				}

			}
			for (String a : body) {
				// System.out.println("The amount value fetched is " + a);
				if (a.contentEquals(objComments.getBody())) {
					isPostUpdated = true;
					break;
				} else {
					isPostUpdated = false;
				}

			}

			for (String a : email) {
				// System.out.println("The amount value fetched is " + a);
				if (a.contentEquals(objComments.getEmail())) {
					isPostUpdated = true;
					break;
				} else {
					isPostUpdated = false;
				}

			}

			if (isPostUpdated) {
				Assert.assertTrue(true);
				System.out.println();
			} else {
				Assert.assertFalse(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Then("User performs a get post request for id {string}")
	public void user_performs_a_get_post_request_for_id(String string) {
		// Write code here that turns the phrase above into concrete actions

		response = when().get((url + "posts" + "/" + string));
		Assert.assertEquals(200, response.getStatusCode());

	}

	/*
	 * This method is to show a specific post based on an particular id eg,
	 * url/posts/10 could be also validated based on data passed from data table in
	 * feature file
	 */
	@Then("User sees the  response with pairs")
	public void user_sees_the_response_with_pairs(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		boolean isPostUpdated = false;
		ArrayList<String> titles = response.then().extract().path("title");
		ArrayList<Integer> id = response.then().extract().path("id");
		ArrayList<Integer> userId = response.then().extract().path("userId");
		ArrayList<String> body = response.then().extract().path("body");

		List<Map<String, String>> objlistmap = dataTable.asMaps(String.class, String.class);

		String titleFrmTable = objlistmap.get(0).get("title");
		String idFrmtable = objlistmap.get(0).get("id");
		String userIdFrmTable = objlistmap.get(0).get("userId");
		String bodyFrmtable = objlistmap.get(0).get("body");

		for (Integer a : id) {
			System.out.println("The amount value fetched is ::::" + a);
			if (String.valueOf(a).contentEquals(idFrmtable)) {
				System.out.println("Inside ID values macteched::");
				isPostUpdated = true;
				break;
			} else {
				System.out.println("Inside ID values not macteched::");
				isPostUpdated = false;
			}

		}
		for (Integer a : userId) {
			if (String.valueOf(a).contentEquals(userIdFrmTable)) {
				isPostUpdated = true;
				break;
			} else {
				isPostUpdated = false;
			}

		}
		for (String a : body) {

			if (a.contentEquals(bodyFrmtable)) {
				isPostUpdated = true;
				break;
			} else {
				isPostUpdated = false;
			}

		}

		for (String a : titles) {
			if (a.contentEquals(titleFrmTable)) {
				isPostUpdated = true;
				break;
			} else {
				isPostUpdated = false;
			}

		}

		if (isPostUpdated) {
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse(false);
		}

	}
	

	@Then("User gets listofallusers and validate for id {string} name {string} username {string} email {string} Chaim_McDermott@dana.io")
	public void user_gets_listofallusers_and_validate_for_id_name_username_email_chaim_mc_dermott_dana_io(String id,
			String name, String username, String email) {
		// Write code here that turns the phrase above into concrete actions
		boolean isUsermatching = false;
		response = when().get((url + "users" + "/" + id));
		ArrayList<Integer> idFrmResp = response.then().extract().path("id");

		ArrayList<String> nameFrmResp = response.then().extract().path("name");
		ArrayList<String> usernameFrmResp = response.then().extract().path("username");
		ArrayList<String> emailFrmResp = response.then().extract().path("email");

		for (Integer a : idFrmResp) {
			if (String.valueOf(a).contentEquals(id)) {
				isUsermatching = true;
				break;
			} else {
				isUsermatching = false;
			}

		}
		for (String a : nameFrmResp) {

			if (a.contentEquals(name)) {
				isUsermatching = true;
				break;
			} else {
				isUsermatching = false;
			}

		}
		for (String a : usernameFrmResp) {

			if (a.contentEquals(username)) {
				isUsermatching = true;
				break;
			} else {
				isUsermatching = false;
			}

		}
		for (String a : emailFrmResp) {

			if (a.contentEquals(email)) {
				isUsermatching = true;
				break;
			} else {
				isUsermatching = false;
			}

		}

		if (isUsermatching) {
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse(false);
		}

	}

//	}





}
