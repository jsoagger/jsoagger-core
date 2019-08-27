/**
 *
 */
package io.github.jsoagger.jfxcore.api;

import java.util.Date;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface INotification {

  NotificationType getType();

  Object getUuid();

  Object getSubjectFullId();

  Date getCreationDate();

}
