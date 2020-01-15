import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Section;
import com.github.ppadial.testrail.client.TestRailClient;
import com.github.ppadial.testrail.client.apiClient.ApiClient;
import com.github.ppadial.testrail.client.model.TRProject;
import com.github.ppadial.testrail.client.model.TRSection;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.ICredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;

import java.util.List;

public class CreateTestRailFromJira {

    public static void main(String[] args) throws Exception {

        /*ApiClient apiClient = new ApiClient("https://bsarvepallitest.testrail.io", Config.TESTRAIL_USERNAME, "Testrail123");
        TestRailClient testRailClient = new TestRailClient(apiClient);
        TRProject trProject = testRailClient.projectApi().getProject(1);*/

        JiraClient jiraClient = new JiraClient("http://bsarvepalli-test.atlassian.net", new BasicCredentials(Config.JIRA_USERNAME, "iHdkd5xOPa3fqXxE88ud898D"));
        Issue issue = jiraClient.getIssue("TEST-1");
        String jira_ID = issue.getId(); //get jira issue id
        String desc = issue.getSummary() ; //get issue title
        String body = issue.getDescription(); //get jira issue body;

        TestRail testRail = TestRail.builder("https://bsarvepallitest.testrail.io", Config.TESTRAIL_USERNAME, "Testrail123").applicationName("playground").build();
        Section section = new Section();
        section.setDescription(issue.getUrl());
        section.setName(jira_ID + ": " + desc);
        testRail.sections().add(1, section).execute();

        /*TRSection section = addSection(testRailClient, issue.getUrl(), jira_ID + ": " + desc);*/

        System.out.println(body);

    }

    public static TRSection addSection(TestRailClient testRailClient, String desc, String name) throws Exception {
       return testRailClient.sectionApi().addSection(1, desc, null, null, name);
    }
}
