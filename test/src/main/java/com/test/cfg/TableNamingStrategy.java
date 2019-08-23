package com.test.cfg;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class TableNamingStrategy extends SpringPhysicalNamingStrategy {
	private static final String PREFIX = "w64jk_5";

    @Override
    public Identifier toPhysicalTableName(Identifier name,
            JdbcEnvironment jdbcEnvironment) {
        return this.addPrefix(this.addUnderScore(name, jdbcEnvironment), jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name,
            JdbcEnvironment jdbcEnvironment) {
        return this.toUpper(this.addUnderScore(name, jdbcEnvironment), jdbcEnvironment);
    }
    
    private Identifier addPrefix(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        String tbName = PREFIX + identifier.toString();
        return new Identifier(tbName, identifier.isQuoted());
    }
    private Identifier toUpper(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        String name = identifier.toString().toUpperCase();
        return new Identifier(name, identifier.isQuoted());
    }
    private Identifier addUnderScore(Identifier name, JdbcEnvironment jdbcEnvironment) {
        if (name == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder(name.getText().replace('.', '_'));
        for (int i = 1; i < builder.length() - 1; i++) {
            if (isUnderscoreRequired(builder.charAt(i - 1), builder.charAt(i),
                    builder.charAt(i + 1))) {
                builder.insert(i++, '_');
            }
        }
        return getIdentifier(builder.toString(), name.isQuoted(), jdbcEnvironment);
    }
    protected Identifier getIdentifier(String name, boolean quoted,
            JdbcEnvironment jdbcEnvironment) {
		if (isCaseInsensitive(jdbcEnvironment)) {
		name = name.toLowerCase(Locale.ROOT);
		}
		return new Identifier(name, quoted);
	}
    protected boolean isCaseInsensitive(JdbcEnvironment jdbcEnvironment) {
        return true;
    }

    private boolean isUnderscoreRequired(char before, char current, char after) {
        return Character.isLowerCase(before) && Character.isUpperCase(current)
                && Character.isLowerCase(after);
    }
}
