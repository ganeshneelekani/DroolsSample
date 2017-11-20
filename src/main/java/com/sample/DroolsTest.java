package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
 
import com.sample.model.Account;

public class DroolsTest {

	
	public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
          //  KieSession kSession = kContainer.newKieSession("ksession-rules");
            
          	KieSession kSession = kContainer.newKieSession("ksession-process");
 
            // go !
            Account account = new Account(200);
            account.withdraw(50);
            kSession.insert(account);
           
            
            kSession.startProcess("SampleFlow",null);
            int fired = kSession.fireAllRules();
            System.out.println("Number of Rules executed = " + fired);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
