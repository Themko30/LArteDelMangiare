package Model;

import java.util.StringJoiner;

public class QueryBuilder {

  private final String table, alias;
  private final StringBuilder query;
  private static final String QM = "?";

  public QueryBuilder(String table, String alias) {
    this.table = table;
    this.alias = alias;
    this.query = new StringBuilder();
  }

  public String generateQuery() {
    String generateQuery = query.toString();
    query.setLength(0);
    return generateQuery;
  }

  public QueryBuilder select(String... fields) {
    query.append("SELECT ");
    if (fields.length == 0) {
      query.append('*');
    } else {
      StringJoiner commaJoiner = new StringJoiner(",");
      for (String field : fields) {
        commaJoiner.add(String.format("%s.%s", alias, field));
      }
      query.append(commaJoiner.toString());
    }
    query.append(" FROM ").append(table).append(" AS ").append(alias);
    return this;
  }

  public QueryBuilder where(String condition) {
    query.append(" WHERE ").append(condition);
    return this;
  }

  public QueryBuilder and() {
    query.append(" AND ");
    return this;
  }

  public QueryBuilder countAll(String... fields) {
    query.append("SELECT COUNT (");
    if (fields.length == 0) {
      query.append('*');
      query.append(")");
    } else {
      StringJoiner commaJoiner = new StringJoiner(",");
      for (String field : fields) {
        commaJoiner.add(String.format("%s.%s", alias, field));
      }
      query.append(commaJoiner.toString());
    }
    query.append(" FROM ").append(table).append(" AS ").append(alias);
    return this;
  }

  public QueryBuilder insert(String... fields) {
    query.append(" INSERT INTO ").append(table).append(' ');
    StringJoiner commaJoiner = new StringJoiner(",", "(", ")");
    for (String field : fields) {
      commaJoiner.add(field);
    }
    query.append(commaJoiner.toString());
    query.append(" VALUES ");
    int numberOfFields = fields.length;
    StringJoiner qmJoiner = new StringJoiner(",", "(", ")");
    do {
      qmJoiner.add(QM);
      numberOfFields--;
    } while (numberOfFields != 0);
    query.append(qmJoiner.toString());
    return this;
  }

  public QueryBuilder delete() {
    query.append("DELETE FROM ").append(table);
    return this;
  }

  public QueryBuilder update(String... fields) {
    query.append("UPDATE ").append(table);
    StringJoiner commaJoiner = new StringJoiner(",");
    for (String field : fields) {
      commaJoiner.add(String.format("%s = %s", field, QM));
    }
    query.append(commaJoiner.toString());
    return this;
  }

  public QueryBuilder limit(boolean withOffset) {
    query.append(" LIMIT ").append(QM);
    if (withOffset) {
      query.append(',').append(QM);
    }
    return this;
  }

  public QueryBuilder innerJoin(String joinedTable, String joinedAlias) {
    query.append(" INNER JOIN ").append(joinedTable).append(' ').append(joinedAlias);
    return this;
  }

  public QueryBuilder outerJoin(boolean isLeft, String joinedTable, String joinedAlias) {
    String direction = isLeft ? " LEFT JOIN" : " RIGHT JOIN";
    query.append(direction).append(' ').append(joinedTable).append(' ').append(joinedAlias);
    return this;
  }

  public QueryBuilder on(String condition) {
    query.append(" ON ").append(condition);
    return this;
  }
}
