/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : フレームワーク
 * Description        : DAOで使用するデータやDAOの処理結果を保持する
 *
 * 2011/07/28      Wangjiquan
 *
 *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

package com.djb.highway.framework.entity;

/**
 * <H3>
 * DAOで使用するデータやDAOの処理結果を保持する.
 * </H3>
 * <PRE>
 * 全てのEntityクラスは、このインターフェースを実装する.
 * </PRE>
 *
 * @author	Wangjiquan
 */
import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -3709111760208163220L;

}
