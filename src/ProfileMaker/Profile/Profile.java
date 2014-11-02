/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfileMaker.Profile;

import CONTROLLER.Calculate;
import ProfileMaker.GitHubExtractor;
import ProfileMaker.GoogleScholarExtractor;
import ProfileMaker.LinkedInExtractor;
import java.util.ArrayList;

public class Profile {

    public String name="", title="", summary="", pic_url="",education="";
    private ArrayList<Experience> experienceList = new ArrayList<Experience>();
    private ArrayList<Project> projectsList = new ArrayList<Project>();
    private ArrayList<Publication> publicationList = new ArrayList<Publication>();

    private Calculate similarityCalculator;

    public Profile(String searchName) {
        
        LinkedInExtractor linkedIn = new LinkedInExtractor();
        GoogleScholarExtractor gscholar = new GoogleScholarExtractor();
        GitHubExtractor github = new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85");

        similarityCalculator=new Calculate();

        linkedIn.Extract1(searchName, this);
        gscholar.Extract(searchName, this);
        github.Extract(searchName, this);
        if (pic_url.equalsIgnoreCase("")) {
            pic_url="http://ryonaitis.files.wordpress.com/2012/03/images.jpg";
        }

        Display();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(ArrayList experienceList) {
        this.experienceList = experienceList;
    }

    public ArrayList getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(ArrayList projectsList) {
        this.projectsList = projectsList;
    }

    public ArrayList getPublicationList() {
        return publicationList;
    }



    public void addProject(Project project) {

        for (Project p : projectsList) {
            if (isSimilar(p.name,project.name)) {
                p.completeInformation(project);
                return;
            }
        }
        projectsList.add(project);
    }

    public void addExperience(Experience experience) {

        for (Experience ex : experienceList) {
            if (isSimilar(ex.name,experience.name)) {

                return;
            }
        }
        experienceList.add(experience);
    }

    public void setPublicationList(ArrayList<Publication> list) {
        this.publicationList = list;
    }

    public void addPublication(Publication publication) {
        for (Publication pb : publicationList) {
            if (isSimilar(pb.name,publication.name)) {
                pb.completeInformation(publication);
                return;
            }
        }

        publicationList.add(publication);
    }
    private boolean isSimilar(String a,String b){

        if (similarityCalculator.calculateNNSimilarity(a, b)>0.8) {
            System.out.println("============================================================hit ");
            System.out.println(a+"   :  "+b);
            return true;
        }
        return false;
    }
    public void Display(){

        System.out.println("\n=========== Research Papers ===========\n");
        for (Publication pb : publicationList) {
            System.out.println(pb.getName());
            System.out.println("Summary :"+pb.getSummary());
            System.out.println("Authors :"+pb.getAuthors());
            System.out.println("---------------\n");
        }
        System.out.println("\n=========== Projects ===========\n");
        for (Project proj : projectsList) {
            System.out.println(proj.getName());
            System.out.println("Summary :" + proj.getSummary());
            System.out.println("---------------\n");
        }

    }
}
