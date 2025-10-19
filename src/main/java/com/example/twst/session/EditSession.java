package com.example.twst.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.example.twst.form.SearchForm;

import lombok.Data;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EditSession implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * searchForm
     */
    private SearchForm searchForm;

    /**
     * @return searchForm
     */
    public SearchForm getSearchForm() {
        return this.searchForm;
    }

    /**
     * 
     * @param searchForm
     */
    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     * tableName
     */
    private String[] tableNameChecks;

    /**
     * @return tableNameChecks
     */
    public String[] getTableNameChecks() {
        return this.tableNameChecks;
    }

    /**
     * @param tableNameChecks
     */
    public void setTableNameChecks(String[] tableNameChecks) {
        this.tableNameChecks = tableNameChecks;
    }

    public EditSession() {
    }
}
