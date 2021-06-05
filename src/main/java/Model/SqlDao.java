package Model;

import javax.sql.DataSource;

public abstract class SqlDao {

  protected final DataSource source;

  protected SqlDao(DataSource source) {
    this.source = source;
  }
}
