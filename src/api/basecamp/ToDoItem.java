package api.basecamp;

import java.util.Calendar;
import java.util.Date;

import org.w3c.dom.Element;

/***
 * Project Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 *
 */
public class ToDoItem extends BaseCampEntity
{

    private int id;
    private int todoListId;
    private String content;
    private Date createdOn;
    private boolean completed;
    private Date dueAt;
    private String responsiblePartyName;

    /***
     * Build Project from XML Element
     * 
     * (Internal Use Only)
     * 
     * @param auth              BCAuth Object
     * @param projectElement    XML Element representation of Project
     */
    ToDoItem(BCAuth auth, Element projectElement)
    {
        super(auth);
        fillFromElement(projectElement);
    }


    public ToDoItem(BCAuth auth, String taskId)
    {
        super(auth);
        Element todoItemElement = super.get("/todo_items/" + taskId + ".xml");
        fillFromElement(todoItemElement);
    }

    private void fillFromElement(Element element)
    {
        this.id = ElementValue.getIntValue(element, "id");
        this.todoListId = ElementValue.getIntValue(element, "todo-list-id");
        this.content = ElementValue.getTextValue(element, "content");
        this.createdOn = ElementValue.getDateValue(element, "created-on").getTime();
        this.completed = ElementValue.getBoolValue(element, "completed");
        Calendar calDueAt = ElementValue.getDateValue(element, "due-at");
        if (calDueAt != null)
        {
            this.dueAt = calDueAt.getTime();
        }
        this.responsiblePartyName = ElementValue.getTextValue(element, "responsible-party-name");
    }

    public int getId()
    {
        return id;
    }

    public int getTodoListId()
    {
        return todoListId;
    }

    public String getContent()
    {
        return content;
    }

    public Date getCreatedOn()
    {
        return createdOn;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public Date getDueAt()
    {
        return dueAt;
    }

    public void setDueAt(Date date)
    {
        this.dueAt = date;
    }

    public String getResponsiblePartyName()
    {
        return responsiblePartyName;
    }

    //--- Getters


}
