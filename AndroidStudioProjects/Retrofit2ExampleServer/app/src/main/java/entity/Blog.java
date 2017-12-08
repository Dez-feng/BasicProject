package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Dez on 2017/11/27.
 */

@DatabaseTable
public class Blog {

    @DatabaseField(generatedId = true)
    public long id;

    @DatabaseField
    public Date date = new Date();

    @DatabaseField(canBeNull = false)
    public String author;

    @DatabaseField(canBeNull = false)
    public String title;

    @DatabaseField(canBeNull = false)
    public String content;

    @Override
    public String toString() {

        return "Blog{" +
                "id=" + id +
                ", date=" + date + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content'" + content +
                '}';

    }
}
