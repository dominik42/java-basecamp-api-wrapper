package api.basecamp;

import org.w3c.dom.Element;

/***
 * Project Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 *
 */
public class ToDoList extends BaseCampEntity
{

    private int id;
    private String name;
    private String description;
    private int projectId;
    private boolean completed;

    /***
     * Build Project from project ID
     * 
     * @param auth		BCAuth Object
     * @param projectId ID of desired project
     */
    public ToDoList(BCAuth auth, int projectId)
    {

        super(auth);

        Element projectElement = super.get("/projects/" + projectId + ".xml");

        this.id = ElementValue.getIntValue(projectElement, "id");
        this.name = ElementValue.getTextValue(projectElement, "name");
        this.description = ElementValue.getTextValue(projectElement, "description");
        this.projectId = ElementValue.getIntValue(projectElement, "project-id");
        this.completed = ElementValue.getBoolValue(projectElement, "completed");
    }

    //	/***
    //	 * 
    //	 * Create and Post new Project
    //	 * 
    //	 * @param auth			BCAuth Object
    //	 * @param projectName	String Name of new Project
    //	 */
    //	public ToDoList(BCAuth auth, String projectName) {
    //		super(auth);
    //
    //		String request;
    //		
    //		request  = "<request>";
    //		request += "<project>";
    //		request += "<name>"+projectName+"</name>";
    //		request += "</project>";
    //		request += "</request>";
    //		
    //		int createdId = super.post("/projects.xml", request);
    //		
    //		Element projectElement = super.get("/projects/"+createdId+".xml");
    //		
    //		this.id				= ElementValue.getIntValue(projectElement,  "id");
    //		this.name 			= ElementValue.getTextValue(projectElement, "name");
    //		this.description 		= ElementValue.getDateTimeValue(projectElement, "created-on");
    //		this.status 		= ElementValue.getTextValue(projectElement, "status");
    //		this.lastChangedOn 	= ElementValue.getDateTimeValue(projectElement, "last-changed-on");
    //		
    //		//get 'subscription' sub element
    //		NodeList nl 		   = projectElement.getElementsByTagName("company");
    //		Element companyElement = (Element)nl.item(0);
    //		
    //		this.companyId 	 = ElementValue.getIntValue(companyElement, "id");
    //		this.companyName = ElementValue.getTextValue(companyElement, "name");
    //		
    //	}

    /***
     * Build Project from XML Element
     * 
     * (Internal Use Only)
     * 
     * @param auth				BCAuth Object
     * @param projectElement	XML Element representation of Project
     */
    ToDoList(BCAuth auth, Element projectElement)
    {

        super(auth);

        this.id = ElementValue.getIntValue(projectElement, "id");
        this.name = ElementValue.getTextValue(projectElement, "name");
        this.description = ElementValue.getTextValue(projectElement, "description");
        this.projectId = ElementValue.getIntValue(projectElement, "project-id");
        this.completed = ElementValue.getBoolValue(projectElement, "completed");
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getProjectId()
    {
        return projectId;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public String toString()
    {
        return name;
    }


}
