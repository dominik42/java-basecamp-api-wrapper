package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * BaseCamp ToDoItem Object
 * 
 * @author Dominik Hirt
 *
 */
public class ToDoItems extends BaseCampEntity
{

    private List<ToDoItem> items = new ArrayList<ToDoItem>();
    private int todoItemCount = 0;

    /***
     * 
     * Get All ToDoLists for given project
     * 
     * @param auth	BCAuth
     */
    public ToDoItems(BCAuth auth, String todoListId, String personId, boolean loadCompleted)
    {
        super(auth);

        StringBuilder request = new StringBuilder("/todo_lists/");
        request.append(todoListId);
        request.append("/todo_items.xml");

        Element todoItemsElement = super.get(request.toString());
        //get entry NodeList
        NodeList nl = todoItemsElement.getElementsByTagName("todo-item");

        for (int i = 0; i < nl.getLength(); i++)
        {
            Element todoItemElement = (Element) nl.item(i);
            ToDoItem todoItem = new ToDoItem(auth, todoItemElement);

            System.out.println("found item: " + todoItem);

            if (matchPerson(todoItem, personId) && matchState(todoItem, loadCompleted))
            {
                System.out.println("add item: " + todoItem);
                this.items.add(todoItem);
                this.todoItemCount++;
            }
        }

    }

    private boolean matchState(ToDoItem todoItem, boolean loadCompleted)
    {
        return loadCompleted || !todoItem.isCompleted();
    }

    private boolean matchPerson(ToDoItem todoItem, String personId)
    {
        return Integer.valueOf(personId) == todoItem.getResponsiblePartyId();
    }

    /**
     * @return List of ToDoList Objects
     */
    public List<ToDoItem> getToDoItems()
    {
        return this.items;
    }

    /**
     * @param 	index	index of ToDoList
     * @return	ToDoList according to index
     */
    public ToDoItem getToDoItem(int index)
    {
        return this.items.get(index);
    }

    /**
     * @return	Count of ToDoItems
     */
    public int getToDoItemCount()
    {
        return this.todoItemCount;
    }


}
