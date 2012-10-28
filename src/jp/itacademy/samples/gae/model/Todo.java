package jp.itacademy.samples.gae.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Todo implements Serializable {

    public static final Comparator<Todo> CREATE_DATE_COMPARATOR =
        new Comparator<Todo>() {
            public int compare(Todo o1, Todo o2) {
                return o1.createDate.compareTo(o2.createDate);
            }
        };

    public static final Comparator<Todo> FINISH_DATE_COMPARATOR =
        new Comparator<Todo>() {
            public int compare(Todo o1, Todo o2) {
                if (o1.finishDate != null && o2.finishDate != null) {
                    return o1.finishDate.compareTo(o2.finishDate);
                }
                if (o1.finishDate == null && o2.finishDate != null) {
                    return -1;
                }
                if (o1.finishDate != null && o2.finishDate == null) {
                    return 1;
                }
                return 0;
            }
        };

    public static final Comparator<Todo> CREATE_DATE_COMPARATOR_R = Collections
        .reverseOrder(CREATE_DATE_COMPARATOR);

    public static final Comparator<Todo> FINISH_DATE_COMPARATOR_R = Collections
        .reverseOrder(FINISH_DATE_COMPARATOR);

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String body;

    private Date createDate;

    private Date finishDate;

    public Todo() {
    }

    public Todo(String body) {
        setBody(body);
    }

    /**
     * Returns the key.
     * 
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     * 
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     * 
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     * 
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    // --

    public long getId() {
        return getKey().getId();
    }

    public boolean isFinished() {
        return finishDate != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Todo other = (Todo) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
