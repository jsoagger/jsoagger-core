/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

package io.github.jsoagger.core.bridge.result;



import java.util.function.Consumer;

import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class OperationCallback {

	private Consumer<IOperationResult>	onSuccess;
	private Consumer<Throwable>			onError;


	/**
	 * Constructor
	 */
	public OperationCallback() {
	}

	/**
	 * @author Ramilafananana VONJISOA
	 * @mailto yvonjisoa@nexitia.com
	 * @date 2019
	 */
	public static class Builder {

		private Consumer<IOperationResult>	onSuccess;
		private Consumer<Throwable>			onError;


		public Builder onSuccess(Consumer<IOperationResult> onSuccess) {
			this.onSuccess = onSuccess;
			return this;
		}


		public Builder onError(Consumer<Throwable> onError) {
			this.onError = onError;
			return this;
		}


		public OperationCallback build() {
			return new OperationCallback(this);
		}
	}


	/**
	 * Constructor
	 *
	 * @param builder
	 */
	private OperationCallback(Builder builder) {
		this.onSuccess = builder.onSuccess;
		this.onError = builder.onError;
	}


	/**
	 * @param result
	 */
	public void onSuccess(IOperationResult result) {
		if (onSuccess != null) {
			onSuccess.accept(result);
		}
	}


	/**
	 * @param result
	 */
	public void onError(Throwable e) {
		if (onError != null) {
			onError.accept(e);
		}
	}


	/**
	 * Getter of onSuccess
	 * 
	 * @return the onSuccess
	 */
	public Consumer<IOperationResult> getOnSuccess() {
		return onSuccess;
	}


	/**
	 * Getter of onError
	 * 
	 * @return the onError
	 */
	public Consumer<Throwable> getOnError() {
		return onError;
	}
}
