package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * Collection of BaseCamp ToDoList Objects
 * 
 * @author Dominik Hirt
 *
 */
public class ToDoLists extends BaseCampEntity
{

    private List<ToDoList> items = new ArrayList<ToDoList>();
    private int todolistCount = 0;

    /***
     * 
     * Get All ToDoLists for the current user
     * 
     * @param auth	BCAuth
     */
    public ToDoLists(BCAuth auth)
    {
        super(auth);

        Element todoListsElement = super.get("/todo_lists.xml");
        //get entry NodeList
        NodeList nl = todoListsElement.getElementsByTagName("todo-list");

        for (int i = 0; i < nl.getLength(); i++)
        {
            Element todoListElement = (Element) nl.item(i);
            ToDoList todoList = new ToDoList(auth, todoListElement);
            this.items.add(todoList);
            this.todolistCount++;
        }
    }

    /***
     * 
     * Get All ToDoLists for given project
     * 
     * @param auth	BCAuth
     */
    public ToDoLists(BCAuth auth, Project project)
    {
        super(auth);

        Element todoListsElement = super.get("/projects/" + project.getId() + "-" + project.getName() + "/todo_lists.xml");
        //get entry NodeList
        NodeList nl = todoListsElement.getElementsByTagName("todo-list");

        for (int i = 0; i < nl.getLength(); i++)
        {
            Element todoListElement = (Element) nl.item(i);
            ToDoList todoList = new ToDoList(auth, todoListElement);
            this.items.add(todoList);
            this.todolistCount++;
        }

    }

    /**
     * @return List of ToDoList Objects
     */
    public List<ToDoList> getToDoLists()
    {
        return this.items;
    }

    /**
     * @param 	index	index of ToDoList
     * @return	ToDoList according to index
     */
    public ToDoList getToDoList(int index)
    {
        return this.items.get(index);
    }

    /**
     * @return	Count of ToDoLists
     */
    public int getToDoListCount()
    {
        return this.todolistCount;
    }

    //    /**
    //     * @return List<ToDoList> of all active ToDoLists
    //     */
    //    public List<ToDoList> getActive()
    //    {
    //
    //        List<ToDoList> returnList = new ArrayList<ToDoList>();
    //
    //        for (int i = 0; i < this.items.size(); i++)
    //        {
    //            ToDoList ToDoList = this.items.get(i);
    //
    //            if (ToDoList.getStatus() == "active")
    //            {
    //                returnList.add(ToDoList);
    //            }
    //        }
    //
    //        return returnList;
    //    }

    //    /**
    //     * @return List<ToDoList> of all ToDoLists that are on hold
    //     */
    //    public List<ToDoList> getOnHold()
    //    {
    //
    //        List<ToDoList> returnList = new ArrayList<ToDoList>();
    //
    //        for (int i = 0; i < this.items.size(); i++)
    //        {
    //            ToDoList ToDoList = this.items.get(i);
    //
    //            if (ToDoList.getStatus() == "on-hold")
    //            {
    //                returnList.add(ToDoList);
    //            }
    //        }
    //
    //        return returnList;
    //    }

    //    /**
    //     * @return List<ToDoList> of all archived ToDoLists
    //     */
    //    public List<ToDoList> getArchived()
    //    {
    //
    //        List<ToDoList> returnList = new ArrayList<ToDoList>();
    //
    //        for (int i = 0; i < this.items.size(); i++)
    //        {
    //            ToDoList ToDoList = this.items.get(i);
    //
    //            if (ToDoList.getStatus() == "archived")
    //            {
    //                returnList.add(ToDoList);
    //            }
    //        }
    //
    //        return returnList;
    //    }
}
