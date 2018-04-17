
package mrhoodie.creations.rest_client_v2.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.*;


public class ListAuthor {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("getAllBooks")
    @Expose
    private List<Object> listBooks = null;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListAuthor() {
    }

    /**
     * 
     * @param id
     * @param name
     * @param listBooks
     */
    public ListAuthor(String id, List<Object> listBooks, String name) {
        super();
        this.id = id;
        this.listBooks = listBooks;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Object> getListBooks() {
        return listBooks;
    }

    public void setListBooks(List<Object> listBooks) {
        this.listBooks = listBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("getAllBooks", listBooks).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(listBooks).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ListAuthor) == false) {
            return false;
        }
        ListAuthor rhs = ((ListAuthor) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(listBooks, rhs.listBooks).isEquals();
    }

}
