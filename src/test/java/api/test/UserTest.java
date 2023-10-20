package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserTest {
	Faker faker;
	User userPayload;
	
	
	@BeforeClass
	public void setupData() {
		
	faker=new Faker();
	userPayload=new User();
		
	userPayload.setId(faker.hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstName(faker.name().username());
	userPayload.setLastName(faker.name().username());
	userPayload.setEmail(faker.internet().emailAddress());
	userPayload.setPassword(faker.internet().password());
	userPayload.setPhone(faker.phoneNumber().cellPhone());

	}
	
	@Test
	public void testPostUser() {
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test
	public void testGetUser() {
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void testUpdateUser() {
		
		userPayload.setFirstName("Nidhi");
		userPayload.setLastName("Kachhadiya");
		Response response=UserEndPoints.UpdateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}
	
	@Test
	public void testDeleteUser() {
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

}
