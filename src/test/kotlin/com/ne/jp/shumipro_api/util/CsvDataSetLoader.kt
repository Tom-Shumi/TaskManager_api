package com.ne.jp.shumipro_api.util

import com.github.springtestdbunit.dataset.AbstractDataSetLoader
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.csv.CsvURLDataSet
import org.springframework.core.io.Resource

// deprecation
class CsvDataSetLoader : AbstractDataSetLoader() {
    override fun createDataSet(resource: Resource): IDataSet {
        return CsvURLDataSet(resource.url)
    }
}