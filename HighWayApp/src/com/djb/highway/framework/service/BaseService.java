/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : フレームワーク
 * Description        : すべての業務ロジックのスーパークラス
 *
 * 2011/07/28      Wangjiquan
 *
 *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

package com.djb.highway.framework.service;

import org.apache.commons.beanutils.PropertyUtils;

import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.framework.entity.BaseEntity;
import com.djb.highway.framework.log.Logger;


/**
 * <H3>業務ロジック実装要の抽象クラス.</H3>
 *
 * <PRE>
 *
 * 全ての業務ロジックは、このクラスを継承する.
 *
 * </PRE>
 *
 * @author Wangjiquan
 */
public class BaseService {

	/**
	 * Logger
	 */
	protected final Logger logger = new Logger(this.getClass());

    /**
     * <H3>コンストラクター.</H3>
     *
     * <PRE>
     * </PRE>
     *
     */
	public BaseService() {
		logger.debug("BaseService", new String[] { ""
				+ getClass().getSimpleName() + " をインスタンス" });
	}

	
	/**
	 * <H3>BaseEntity to BaseDTO</H3>
	 * 
	 * @param dTO
	 * @param entity
	 * @return Object
	 */
	protected final Object entityToDTO(BaseDTO dTO, BaseEntity entity) {
		try {
			PropertyUtils.copyProperties(dTO, entity);
		} catch (Exception e) {
			logger.error("entityToDTO", e);
			
		}
		return dTO;
	}
	


}