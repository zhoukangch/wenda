package com.nowcoder;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InitDatabaseTests {

    @Autowired
	UserDAO userDAO;

    @Autowired
	QuestionDAO questionDAO;
	@Test
	public void initDatabaseTests() {
		Random random=new Random();
        /*
		for (int i=0;i<11;i++){
			User user=new User();
			user.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
			user.setName(String.format("USER%d",i));
			user.setPassword("");
			user.setSalt("");
			userDAO.addUser(user);
		}*/

        /*
        List<User> users=userDAO.selectALLUsers();
        for(int i=0;i<users.size();i++){
        	System.out.println("name :"+users.get(i).getName()+"  head_url :"+users.get(i).getHead_url());
		}
         */
        /*
        User user=userDAO.selectUserById(1);
        System.out.println(user.getHead_url());
        System.out.println(user.getPassword());
        */
       /*
        for (int i=0;i<11;i++){
        	Question question=new Question();
        	question.setTitle("question "+i);
        	question.setContent("Are you a pig ?");
        	question.setCreated_date(new Date());
        	question.setComment_count(i+100);
        	question.setUser_id(i);
        	questionDAO.addQuestion(question);
		}
         */
       /*
		List<Question>  questionsList=questionDAO.selectLatestQuestions(0,10);
		for (int i=0;i<questionsList.size();i++){
			Question question = questionsList.get(i);
			System.out.println(question.toString());
		}
		*/
		//Question question = questionsList.get(1);

		//System.out.println(questions.toString());
		//System.out.println(questions.getCreated_date());
	}

}
