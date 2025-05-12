package com.example.twst.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.twst.form.SearchForm;

import lombok.Data;

@Data
@Component
@SessionScope
@SuppressWarnings("serial")
public class EditSession implements Serializable {

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

}
