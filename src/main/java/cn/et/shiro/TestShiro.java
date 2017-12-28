package cn.et.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiro {

	public static void main(String[] args) {
		//��ini�ж�ȡȨ����Ϣ ����SecurityManager����
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:my.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		
		//��ȡ��ǰ�û�
		Subject currentUser = SecurityUtils.getSubject();
		//��ǰ�û��ĻỰ
		Session session = currentUser.getSession();
		//�ж��Ƿ��¼  δ��¼ ����Ҫ��¼
		/**  
         * �û�����������   
       *    principals and credentials  
         *     principals�����ˣ���ʾ�û��ı�ʶ��Ϣ �����û��� �û���ַ��  
         *     credentials��ƾ֤����ʾ�û����ڵ�¼��ƾ֤ �������� ֤���  
         */  
		if ( !currentUser.isAuthenticated() ) {
			
			//�û�������û���������
			UsernamePasswordToken token = new UsernamePasswordToken("jiaozi", "123456");
			token.setRememberMe(true);
			try {
				currentUser.login(token);
				System.out.println("��¼�ɹ�");
				System.out.println(currentUser.isAuthenticated());
				System.out.println(currentUser.isRemembered());
				//����¼����û��Ƿ�ӵ��ĳ����ɫ
				if(currentUser.hasRole("role1")){
					System.out.println("�Ƿ�ӵ��role1��ɫ");
				}
				
				if(currentUser.isPermitted("user:delete:2")){
					System.out.println("ӵ�в�ѯ1�ŵ�Ȩ��");
				}
				
			} catch ( UnknownAccountException uae ) {
			    System.out.println("�˺Ŵ���");
			} catch ( IncorrectCredentialsException ice ) {
				 System.out.println("���벻ƥ��");
			} catch ( LockedAccountException lae ) {
				 System.out.println("�˺ű�����");
			} catch ( AuthenticationException ae ) {
				 System.out.println("λ���쳣");
			}
			
		}
	}

}
