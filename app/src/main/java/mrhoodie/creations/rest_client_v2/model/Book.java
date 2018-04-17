
package mrhoodie.creations.rest_client_v2.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.*;


public class Book {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("listAuthor")
    @Expose
    private List<ListAuthor> listAuthor = null;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Book() {
    }

    /**
     * 
     * @param id
     * @param title
     * @param price
     * @param description
     * @param publishedDate
     * @param type
     * @param listAuthor
     */
    public Book(String description, String id, List<ListAuthor> listAuthor, Double price, String publishedDate, String title, String type) {
        super();
        this.description = description;
        this.id = id;
        this.listAuthor = listAuthor;
        this.price = price;
        this.publishedDate = publishedDate;
        this.title = title;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ListAuthor> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(List<ListAuthor> listAuthor) {
        this.listAuthor = listAuthor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("description", description).append("id", id).append("listAuthor", listAuthor).append("price", price).append("publishedDate", publishedDate).append("title", title).append("type", type).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(title).append(price).append(description).append(publishedDate).append(type).append(listAuthor).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Book) == false) {
            return false;
        }
        Book rhs = ((Book) other);
        return new EqualsBuilder().append(id, rhs.id).append(title, rhs.title).append(price, rhs.price).append(description, rhs.description).append(publishedDate, rhs.publishedDate).append(type, rhs.type).append(listAuthor, rhs.listAuthor).isEquals();
    }

}
