package org.tara.tools.tokenizer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({

	TokenizerIdentifierTests.class,
	TokenizerKeywordTests.class,
	TokenizerOperatorTests.class,
	TokenizerFloatingPointTests.class,
	TokenizerIntegerTests.class,
	TokenizerCommentTests.class,
	TokenizerEdgeCasesTests.class,
	TokenizerCommonCasesTests.class,
	
})
public class TokenizerTestSuite {

}
