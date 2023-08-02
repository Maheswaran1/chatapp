package proxy;

import java.lang.reflect.Proxy;
import com.zoho.RedisDao.EmailRedisDao;
import com.zoho.RedisDao.EmailRedisSet;
import com.zoho.RedisDao.SessionRedisDao;
import com.zoho.RedisDao.SessionRedisSet;
import com.zoho.RedisDao.UserRedisDao;
import com.zoho.RedisDao.UserRedisSet;
import com.zoho.api.EmailDao;
import com.zoho.api.SessionManagerDao;
import com.zoho.api.UserDao;
import apiInterFace.EmailInterface;
import apiInterFace.SessionInterface;
import apiInterFace.UserInterface;

public class ProxyFactory {
		public  static UserInterface getUserProxy() {
			ObjectInvocationHandler objectInvocation;
			if(redispresent()) {
				UserRedisSet userDao = new UserRedisSet();
				UserRedisDao userRedis = new UserRedisDao();
				 objectInvocation = new ObjectInvocationHandler(userRedis,userDao);
			}else {
				objectInvocation = new ObjectInvocationHandler(new UserDao(),null);
			}
			
			UserInterface user = (UserInterface) Proxy.newProxyInstance(UserInterface.class.getClassLoader(),new  Class[] {UserInterface.class}, objectInvocation);
			return user;
		}

		public static EmailInterface getEmailProxy() {
			ObjectInvocationHandler objectInvocation;
			if(redispresent()) {
			EmailRedisDao emailRedis = new EmailRedisDao();
			EmailRedisSet  emailDao = new EmailRedisSet();
			objectInvocation = new ObjectInvocationHandler(emailRedis,emailDao);
			}else {
				objectInvocation = new ObjectInvocationHandler(new EmailDao(),null);
			}
			EmailInterface email = (EmailInterface) Proxy.newProxyInstance(EmailInterface.class.getClassLoader(),new  Class[] {EmailInterface.class}, objectInvocation);
			return email;
		}
		
		public static SessionInterface getSessionProxy() {
			ObjectInvocationHandler objectInvocation;
			if(redispresent()) {
				SessionRedisSet sessionDao = new SessionRedisSet();
				SessionRedisDao sessionRedis = new SessionRedisDao();
				objectInvocation = new ObjectInvocationHandler(sessionRedis,sessionDao);
			}else {
				objectInvocation = new ObjectInvocationHandler(new SessionManagerDao(),null);
			}
			
			SessionInterface session = (SessionInterface) Proxy.newProxyInstance(SessionInterface.class.getClassLoader(),new  Class[] {SessionInterface.class}, objectInvocation);
			return session;
		}
		
		
		private static boolean redispresent() {
			return true;
		}
}
